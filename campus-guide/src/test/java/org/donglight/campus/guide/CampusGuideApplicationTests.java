package org.donglight.campus.guide;

import org.donglight.campus.guide.repository.ScenicSpotRepository;
import org.donglight.campus.guide.repository.DistanceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampusGuideApplicationTests {

    @Autowired
    private ScenicSpotRepository scenicSpotRepository;

    @Autowired
    private DistanceRepository distanceRepository;

    @Test
    public void contextLoads() {
        //personRepository.findSortestPath("p1", "p3").forEach(System.out::println);
        /*ScenicSpot attractions1 = new ScenicSpot("ZHKU005", "学校大门", "门口");
        ScenicSpot attractions2 = new ScenicSpot("ZHKU006", "行政楼", "行政楼");
        ScenicSpot attractions3 = new ScenicSpot("ZHKU007", "教学楼", "教学楼");
        ScenicSpot attractions4 = new ScenicSpot("ZHKU008", "操场", "操场");
        ScenicSpot attractions5 = new ScenicSpot("ZHKU009", "图书馆", "图书馆");
        ScenicSpot attractions6 = new ScenicSpot("ZHKU0010", "英东楼", "英东楼");
        Distance distance1 = new Distance(100L, attractions1, attractions2);
        Distance distance2 = new Distance(100L, attractions1, attractions3);
        Distance distance3 = new Distance(100L, attractions1, attractions4);
        Distance distance4 = new Distance(100L, attractions1, attractions5);
        Distance distance5 = new Distance(100L, attractions1, attractions6);
        attractions1.setDistance(Arrays.asList(distance1, distance2, distance3, distance4, distance5));
        attractionsRepository.save(attractions1);*/
        //attractionsRepository.deleteById(208L);
    }

}
