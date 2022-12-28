package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.model.DataPrice;
import com.workshop.carauctionsystem.repository.BrandRepository;
import com.workshop.carauctionsystem.repository.DataPriceRepository;
import com.workshop.carauctionsystem.service.DataPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataPriceServiceImpl implements DataPriceService {

    @Autowired
    DataPriceRepository dataPrice;

    @Override
    public List<DataPrice> getPriceAVG() {
        return dataPrice.priceAVG();
    }
}
