package com.saurabh.service.serviceimp;

import com.saurabh.RadioStationApiApplication;
import com.saurabh.dto.ProgramDto;
import com.saurabh.dto.RadioStationDto;
import com.saurabh.entity.Program;
import com.saurabh.entity.RadioStation;

import com.saurabh.repository.RadioStationRepository;
import com.saurabh.service.RadioStationImplement;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class RadioStationService implements RadioStationImplement {
    @Autowired
    private RadioStationRepository radioStationRepository;

    public RadioStationService(RadioStationRepository radioStationRepository) {
        this.radioStationRepository = radioStationRepository;
    }

    @Override
    public List<RadioStationDto> fetchAllRadioStation() {
  List<RadioStation>radioStations= (List<RadioStation>) radioStationRepository.findAll();
        List<RadioStationDto> radioStationDtos = new ArrayList<>();
        for (RadioStation radioStation : radioStations) {
            RadioStationDto radioStationDto = new RadioStationDto(radioStation);
            radioStationDtos.add(radioStationDto);
        }
        return radioStationDtos;
    }

    @Override
    public RadioStationDto fetchRadioStationbyId(Long stationId) {
        RadioStation radioStation = radioStationRepository.findById(stationId).get();
        RadioStationDto dto=new RadioStationDto(radioStation);
        if (radioStation == null) {
            throw new RuntimeException("Given Id " + stationId + "there are not Radio Station are Available ");
        }
        return dto;
    }

    @Override
    @Transactional
    public void addNewRadioStation(RadioStation radioStation) {
        radioStationRepository.save(radioStation);
    }

    @Override
    @Transactional
    public void updateRadioStation(Long stationId, RadioStation radioStatio) {
        RadioStation radioStation = radioStationRepository.findById(stationId).get();
        if (radioStation == null) {
            throw new RuntimeException("Given Id " + stationId + "there are not Radio Station are Available ");
        } else {
            radioStation.setCity(radioStatio.getCity());
            radioStation.setGenre(radioStatio.getGenre());
            radioStation.setFrequency(radioStatio.getFrequency());
            radioStation.setContactEmail(radioStatio.getContactEmail());
            radioStation.setContactPhone(radioStatio.getContactPhone());
            radioStation.setWebsite(radioStatio.getWebsite());
            radioStation.setPrograms(radioStatio.getPrograms());
            radioStationRepository.save(radioStation);
        }

    }

    @Override
    @Transactional
    public void deleteRadioStation(Long stationId) {
        RadioStation radioStation = radioStationRepository.findById(stationId).get();
        if (radioStation == null) {
            throw new RuntimeException("Given Id " + stationId + "there are not Radio Station are Available ");
        } else {
            radioStationRepository.delete(radioStation);
        }
    }

    public List<Object[]> findAllDetailsForStation(Long stationId) {
        RadioStation radioStation = radioStationRepository.findById(stationId).get();
        if (radioStation == null) {
            throw new RuntimeException("Given Id " + stationId + "there are not Radio Station are Available ");
        }
       return radioStationRepository.findAllDetailsForStation(stationId);
    }
    @Override
    @Transactional
    public List<Object[]> findProgramDetailsByDate(LocalDate programDate) {
//        if (programDate==null)
//        {
//            throw new RuntimeException("Some Thing went Wrong");
//        }
//        return radioStationRepository.findProgramDetailsByDate(programDate);
        return null;
    }

}



