package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.*;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.CarDTO;
import com.workshop.carauctionsystem.model.CarSpecificationDTO;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.model.SafetySystemDTO;
import com.workshop.carauctionsystem.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
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


    @GetMapping("/admin/car")
    public ModelAndView showListCar(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "id") String id,
                                    @RequestParam(defaultValue = "") String search, Model model) {
        ModelAndView view = new ModelAndView();
        model.addAttribute("car", new CarDTO());

        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("modelCar", modelService.getAllModelByStatus());
        model.addAttribute("createBy", userService.getRoleByAdminCar());
        model.addAttribute("CarDTO",new CarDTO());
        model.addAttribute("CarSpecDTO",new CarSpecificationDTO());
        model.addAttribute("Safety",new SafetySystemDTO());

        Page<Car> lstCar = carService.findAllByCarName(PageRequest.of(page, 5, Sort.by(id)), search);

        if (!lstCar.isEmpty()) {
            view = new ModelAndView("admin/listCar");
            view.addObject("cars", lstCar);
        } else {
            view = new ModelAndView("admin/listCar");
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
                            @ModelAttribute(value = "CarSpecDTO")CarSpecificationDTO carSpecDTO,
                            @ModelAttribute(value = "Safety")SafetySystemDTO safetyDTO,
                            @RequestParam Map<String, String> requestMap,
                            RedirectAttributes ra) {
        Long modelId = Long.parseLong(requestMap.get("modelId"));
        int createById = Integer.parseInt(requestMap.get("createById"));
        String statusCar = requestMap.get("statusCar");
        String absBrake = requestMap.get("absBrake");
        String speedControl = requestMap.get("speedControl");
        String tirePressure = requestMap.get("tirePressure");

        try{
            ModelCar modelCar = new ModelCar();
            modelCar.setId(modelId);
            User userCreate = new User();
            userCreate.setId(createById);

            Car car = new Car();
            car.setCarName(carDTO.getCarName());
            car.setDescription(carDTO.getDescription());
            car.setUpBoundPrice(Long.valueOf(carDTO.getUpBoundPrice()));
            car.setDownBoundPrice(Long.valueOf(carDTO.getDownBoundPrice()));
            car.setModelId(modelCar);
            car.setCreatedBy(userCreate);
            car.setStatus(1);
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            car.setCreatedAt(timestamp);
            car.setUpdatedAt(timestamp);

            carService.saveCar(car);
            Long carID = car.getId();
            if (carID != null){
                CarSpecification carSpec = new CarSpecification();
                carSpec.setCarId(car);
                carSpec.setManufacturing(carSpecDTO.getManufacturing());
                carSpec.setStatus(statusCar);
                carSpec.setKm_driven(carSpecDTO.getKm_driven());
                carSpec.setGear(carSpecDTO.getGear());
                carSpec.setFuel(carSpecDTO.getFuel());
                carSpec.setFuelConsumption(carSpecDTO.getFuelConsumption());
                carSpec.setOuterColor(carSpecDTO.getOuterColor());
                carSpec.setInnerColor(carSpecDTO.getInnerColor());
                carSpec.setOverallDimension(carSpecDTO.getOverallDimension());
                carSpec.setDrive(carSpecDTO.getDrive());
                carSpec.setYearOfMake(carSpecDTO.getYearOfMake());
                caeSpecService.saveCarSpecification(carSpec);
            }
            if(carID != null){
                SafetySystem safetySystem = new SafetySystem();
                safetySystem.setCarId(car);
                safetySystem.setAir_bag(safetyDTO.getAir_bag());
                safetySystem.setAbs_brake(absBrake);
                safetySystem.setSpeedControl(speedControl);
                safetySystem.setTirePressure(tirePressure);
                safetySystem.setOtherDescription(safetyDTO.getOtherDescription());
                safetySystemService.saveSafetySystem(safetySystem);
            }
            ra.addFlashAttribute("success", "The Car has been saved successfully");
            return "redirect:/admin/car";
        }catch (Exception e){
            ra.addFlashAttribute("fail", "Add New Car Failed");
            return "redirect:/admin/car";
        }
    }

    @PostMapping("/admin/car/edit")
    public String update(@RequestParam Map<String, String> requestMap,
                         RedirectAttributes ra) {
        try {
            Long id = Long.parseLong(requestMap.get("id"));// carId
            String carName = requestMap.get("carName");
            Long upBound = Long.parseLong(requestMap.get("upBound"));
            Long downBound = Long.parseLong(requestMap.get("downBound"));
            String description = requestMap.get("description");
            String createdAt = requestMap.get("createdAt");
            Long createById = Long.parseLong(requestMap.get("createById"));
            Date date = new Date();
            Timestamp updateAt = new Timestamp(date.getTime());
            //saveCar
            carService.updateCar(createById,description,upBound,downBound,updateAt,carName,id);

            Long idCarSpec = Long.parseLong(requestMap.get("idCarSpec"));
            String manufacturing = requestMap.get("manufacturing");
            String statusSpec = "Old";
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
            caeSpecService.update(manufacturing,km_driven,gear,fuel,fuelConsumption,outerColor,innerColor,overallDimension,drive,yearOfMake,idCarSpec);

            Long idSafe = Long.parseLong(requestMap.get("idSafe"));
            String air_bag = requestMap.get("air_bag");
            String abs_brake = requestMap.get("abs_brake");
            String speedControl = requestMap.get("speedControl");
            String tirePressure = requestMap.get("tirePressure");
            String otherDescription = requestMap.get("otherDescription");
            //save safety
            safetySystemService.update(air_bag,abs_brake,speedControl,tirePressure,otherDescription,idSafe);
            ra.addFlashAttribute("success", "The Car has been saved successfully");
            return "redirect:/admin/car";
        }catch (Exception e){
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
            return "page404";
        }
        ra.addFlashAttribute("success", "The Car has been deleted successfully");
        return "redirect:/admin/car";
    }
}
