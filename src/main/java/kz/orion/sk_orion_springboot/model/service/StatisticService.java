package kz.orion.sk_orion_springboot.model.service;

import kz.orion.sk_orion_springboot.model.entity.Statistic;
import kz.orion.sk_orion_springboot.model.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public void save(Statistic statistic){
        statisticRepository.save(statistic);
    }

    public List<Statistic> findAll(){
        List<Statistic> statisticList = statisticRepository.findAll();
        return statisticList;
    }


}
