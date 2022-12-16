package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.*;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.CarDTO;
import com.workshop.carauctionsystem.model.CarSpecificationDTO;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.model.SafetySystemDTO;
import com.workshop.carauctionsystem.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AdminCarController {
    @Autowired
    CarService carService;

    @Autowired
    BrandService brandService;

    @Autowired
    ModelService modelService;

    @Autowired
    private UserService userService;
    @Autowired
    private SafetySystemService safetySystemService;
    @Autowired
    private CarSpecificationService caeSpecService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/admin/car")
    public ModelAndView showListCar(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "id") String id,
                                    @RequestParam(defaultValue = "") String search, Model model,
                                    @CookieValue(value = "setUser", defaultValue = "") String setUser) {
        ModelAndView view = new ModelAndView();
        model.addAttribute("car", new CarDTO());
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("modelCar", modelService.getAllModelByStatus());
        model.addAttribute("createBy", userService.getRoleByAdminCar());
        model.addAttribute("CarDTO", new CarDTO());
        model.addAttribute("CarSpecDTO", new CarSpecificationDTO());
        model.addAttribute("Safety", new SafetySystemDTO());

        Page<Car> lstCar = carService.findAllByCarName(PageRequest.of(page, 5, Sort.by(id)), search);
        view = new ModelAndView("admin/listCar");
        if (!lstCar.isEmpty()) {
            view.addObject("cars", lstCar);
        } else {
            view.addObject("lst_empty", "List Empty!");
        }
        return view;
    }

    @GetMapping("/car/all")
    public ResponseEntity<ResponseObject> getCar() {
        List<Car> lc = carService.findAllDTO();
        if (!lc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Existed!", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Valid!", null));
    }

    @PostMapping("admin/car/create")
    public String createCar(@ModelAttribute(value = "CarDTO") CarDTO carDTO,
                            @ModelAttribute(value = "CarSpecDTO") CarSpecificationDTO carSpecDTO,
                            @ModelAttribute(value = "Safety") SafetySystemDTO safetyDTO,
                            @RequestParam(value = "upImg") MultipartFile[] upImg,
                            @RequestParam Map<String, String> requestMap,
                            RedirectAttributes ra) {

        if (requestMap.get("modelId").equals("") || requestMap.get("createById").equals("") || carDTO.getUpBoundPrice() == null ||
                carDTO.getDownBoundPrice() == null || carDTO.getDescription().equals("") || requestMap.get("statusCar").equals("") ||
                carSpecDTO.getManufacturing().equals("") || carSpecDTO.getKm_driven().equals("") || carSpecDTO.getGear().equals("") ||
                carSpecDTO.getFuel().equals("") || !carSpecDTO.getOverallDimension().equals("") || carSpecDTO.getFuelConsumption().equals("") ||
                carSpecDTO.getOuterColor().equals("") || carSpecDTO.getInnerColor().equals("") || carSpecDTO.getDrive().equals("") ||
                carSpecDTO.getYearOfMake().equals("") || safetyDTO.getAir_bag().equals("") || requestMap.get("absBrake").equals("") ||
                requestMap.get("speedControl").equals("") || requestMap.get("tirePressure").equals("") || safetyDTO.getOtherDescription().equals("")) {
            ra.addFlashAttribute("fail", "Add New Car Failed");
            return "redirect:/admin/car";
        }
        Long modelId = Long.parseLong(requestMap.get("modelId"));
        int createById = Integer.parseInt(requestMap.get("createById"));
        String statusCar = requestMap.get("statusCar");
        String absBrake = requestMap.get("absBrake");
        String speedControl = requestMap.get("speedControl");
        String tirePressure = requestMap.get("tirePressure");
        List<String> photos = new ArrayList<>();
        for (MultipartFile file : upImg) {
            try {
                photos.add(file.getOriginalFilename());
                FileCopyUtils.copy(file.getBytes(), new File("src\\main\\resources\\static\\assets\\hoang/" + file.getOriginalFilename()));
            } catch (IOException e) {
                ra.addFlashAttribute("fail", "Add New Car Failed (Can't find pictures)");
                return "redirect:/admin/car";
            }
        }
        try {
            ModelCar modelCar = new ModelCar();
            modelCar.setId(modelId);
            User userCreate = new User();
            userCreate.setId(createById);

            Car car = new Car();
            car.setCarName(carDTO.getCarName());
            String regex = "^[a-zA-Z0-9 \\-*_]+$";
            if (!carDTO.getCarName().matches(regex) || carDTO.getDescription().equals("")) {
                ra.addFlashAttribute("fail", "Add New Car Failed");
                return "redirect:/admin/car";
            }
            car.setDescription(carDTO.getDescription());
            Long up = carDTO.getUpBoundPrice();
            Long down = carDTO.getDownBoundPrice();
            car.setUpBoundPrice(up);
            car.setDownBoundPrice(down);
            car.setModelId(modelCar);
            car.setCreatedBy(userCreate);
            car.setStatus(1);
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            car.setCreatedAt(timestamp);
            car.setUpdatedAt(timestamp);
            if (up < down) {
                ra.addFlashAttribute("fail", "Add New Car Failed (PriceUp not less PriceDown)");
                return "redirect:/admin/car";
            }
            carService.saveCar(car);
            Long carID = car.getId();
            // add Car Specification
            if (carID != null) {
                CarSpecification carSpec = new CarSpecification();
                carSpec.setCarId(car);
                carSpec.setManufacturing(carSpecDTO.getManufacturing());
                carSpec.setStatus(statusCar);
                carSpec.setKm_driven(carSpecDTO.getKm_driven());
                carSpec.setGear(carSpecDTO.getGear());
                carSpec.setFuel(carSpecDTO.getFuel());
                carSpec.setFuelConsumption(carSpecDTO.getFuelConsumption() + "L/100KM");
                carSpec.setOuterColor(carSpecDTO.getOuterColor());
                carSpec.setInnerColor(carSpecDTO.getInnerColor());
                carSpec.setOverallDimension(carSpecDTO.getOverallDimension());
                carSpec.setDrive(carSpecDTO.getDrive());
                carSpec.setYearOfMake(carSpecDTO.getYearOfMake());
                caeSpecService.saveCarSpecification(carSpec);

                SafetySystem safetySystem = new SafetySystem();
                safetySystem.setCarId(car);
                safetySystem.setAir_bag(safetyDTO.getAir_bag());
                safetySystem.setAbs_brake(absBrake);
                safetySystem.setSpeedControl(speedControl);
                safetySystem.setTirePressure(tirePressure);
                safetySystem.setOtherDescription(safetyDTO.getOtherDescription());
                safetySystemService.saveSafetySystem(safetySystem);


                for (String string : photos) {
                    Image image = new Image();
                    image.setCarId(car);
                    image.setImgPath("/hoang/" + string);
                    imageService.saveImageForCar(image);
                }

            } else {
                ra.addFlashAttribute("fail", "Add New Car Failed");
                return "redirect:/admin/car";
            }
            ra.addFlashAttribute("success", "The Car has been saved successfully");
            return "redirect:/admin/car";
        } catch (Exception e) {
            ra.addFlashAttribute("fail", "Add New Car Failed");
            return "redirect:/admin/car";
        }

    }

    @PostMapping("/admin/car/edit")
    public String update(@RequestParam Map<String, String> requestMap,
                         RedirectAttributes ra) {

        String regex = "^[a-zA-Z0-9 \\-*_]+$";
        String regex1 = "^[a-zA-Z \\-]+$";
        String regNumberInteger = "^\\d+$";
        try {
            if (requestMap.get("upBound").equals("") || requestMap.get("downBound").equals("") || requestMap.get("modelId").equals("") ||
                    requestMap.get("createById").equals("") || !requestMap.get("manufacturing").matches(regex1) || requestMap.get("km_driven").equals("") ||
                    requestMap.get("gear").equals("") || requestMap.get("fuel").equals("") || requestMap.get("overallDimension").equals("") ||
                    requestMap.get("fuelConsumption").equals("") || !requestMap.get("outerColor").matches(regex1) || !requestMap.get("innerColor").matches(regex1) ||
                    requestMap.get("drive").equals("") || requestMap.get("yearOfMake").equals("") || requestMap.get("air_bag").equals("") ||
                    !requestMap.get("abs_brake").matches(regex1) || !requestMap.get("speedControl").matches(regex1) || !requestMap.get("tirePressure").matches(regex1) ||
                    requestMap.get("otherDescription").equals("")) {
                ra.addFlashAttribute("fail", "Update Car Failed");
                return "redirect:/admin/car";
            }
            Long id = Long.parseLong(requestMap.get("id"));// carId
            String carName = requestMap.get("carName");
            Long upBound = Long.parseLong(requestMap.get("upBound"));
            Long downBound = Long.parseLong(requestMap.get("downBound"));
            String description = requestMap.get("description");
            Long createById = Long.parseLong(requestMap.get("createById"));
            Date date = new Date();
            Timestamp updateAt = new Timestamp(date.getTime());
            //saveCar
            if (upBound < downBound || !carName.matches(regex) || description.equals("")) {
                ra.addFlashAttribute("fail", "Add New Car Failed");
                return "redirect:/admin/car";
            }
            carService.updateCar(createById, description, upBound, downBound, updateAt, carName, id);

            Long idCarSpec = Long.parseLong(requestMap.get("idCarSpec"));
            String manufacturing = requestMap.get("manufacturing");
            String km_driven = requestMap.get("km_driven");
            String gear = requestMap.get("gear");
            String fuel = requestMap.get("fuel");
            String overallDimension = requestMap.get("overallDimension");
            String fuelConsumption = requestMap.get("fuelConsumption");
            String outerColor = requestMap.get("outerColor");
            String innerColor = requestMap.get("innerColor");
            String drive = requestMap.get("drive");
            String yearOfMake = requestMap.get("yearOfMake");
            //save carSpec
            int km = Integer.parseInt(km_driven);
            int year = Integer.parseInt(yearOfMake);
            Year thisYear = Year.now();
            int yearNow = Integer.parseInt(String.valueOf(thisYear)) + 1;
            if (!km_driven.matches(regNumberInteger) || !yearOfMake.matches(regNumberInteger) ||
                    km < 0 || year < 2000 || year > 2100 || year < yearNow) {
                    ra.addFlashAttribute("fail", "Update Car Failed");
                    return "redirect:/admin/car";
            }
            caeSpecService.update(manufacturing, km_driven, gear, fuel, fuelConsumption, outerColor, innerColor, overallDimension, drive, yearOfMake, idCarSpec);
            Long idSafe = Long.parseLong(requestMap.get("idSafe"));
            String air_bag = requestMap.get("air_bag");
            String abs_brake = requestMap.get("abs_brake");
            String speedControl = requestMap.get("speedControl");
            String tirePressure = requestMap.get("tirePressure");
            String otherDescription = requestMap.get("otherDescription");
            //save safety
            if (air_bag.matches(regNumberInteger)) {
                int ari = Integer.parseInt(air_bag);
                if (ari < 0) {
                    ra.addFlashAttribute("fail", "Update Car Failed");
                    return "redirect:/admin/car";
                }
            }
            safetySystemService.update(air_bag, abs_brake, speedControl, tirePressure, otherDescription, idSafe);
            ra.addFlashAttribute("success", "The Car has been saved successfully");
            return "redirect:/admin/car";
        } catch (Exception e) {
            ra.addFlashAttribute("fail", "Update Car Failed");
            return "redirect:/admin/car";
        }
    }

    @GetMapping("/admin/car/delete/{id}")
    public String deleteModel(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            carService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "admin/page404";
        }
        ra.addFlashAttribute("success", "The Car has been deleted successfully");
        return "redirect:/admin/car";
    }
}
