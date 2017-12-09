package com.besmart.services.schedualer;

import com.besmart.model.Filter;
import com.besmart.model.enums.GetBy;
import com.besmart.model.enums.State;
import com.besmart.model.pojo.Triangle;
import com.besmart.services.TriangleService;
import com.besmart.utils.Configurations;
import com.besmart.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shirela on 12/7/2017.
 */
@Service
@EnableScheduling
public class AlgoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlgoService.class);
    @Autowired
    TriangleService triangleService;

    @Autowired
    protected Configurations configurations;

    @Autowired
    Filter filter;

    /**
     * Get 2 edges' triangle storage, calculate the third edge and save it in the storage
     */
    @Scheduled(fixedDelayString = "${fixed.delay.in.milliseconds}")
    public void runAlgoSrv() {
        try {

            LOGGER.debug(Constants.ALGO_SERVICE_DO_THE_JOB_AT + new Date());

            //Prepare filter
            filter.setGetBy(GetBy.STATE);
            filter.setState(State.PRECALC);

            List<Triangle> trianglesPreCalc = triangleService.getEntitiesByFilter(filter); //Get data by filter

            //Calc triangles
            List<Triangle> trianglesPostCalc = new ArrayList<>();
            getTrianglesAfterCalc(trianglesPreCalc, trianglesPostCalc);

            //Update triangles after calc
            trianglesPostCalc.stream().forEach(current -> triangleService.editEntity(current, GetBy.IDS));
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).append("\n").append(e).toString());
        }


    }

    /**
     * @param trianglesPreCalc
     * @param trianglesPostCalc
     */
    private void getTrianglesAfterCalc(List<Triangle> trianglesPreCalc, List<Triangle> trianglesPostCalc) {
        trianglesPreCalc.stream().forEach(current ->
                trianglesPostCalc.add(
                        new Triangle(current.getId(), current.getFirstEdge(), current.getSecondEdge(), getHypotenuse(current), State.POSTCALC)
                )
        );
    }

    /**
     * @param triangle
     * @return
     */
    private String getHypotenuse(Triangle triangle) {
        DecimalFormat df = new DecimalFormat(configurations.getNumberOfDigitAfterPoint());
        return df.format(Math.sqrt(
                Math.pow(Double.valueOf(triangle.getFirstEdge()), 2) +
                        Math.pow(Double.valueOf(triangle.getSecondEdge()), 2)
                )
        );
    }

}
