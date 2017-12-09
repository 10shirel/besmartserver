package com.besmart.api;

import com.besmart.model.Filter;
import com.besmart.model.enums.GetBy;
import com.besmart.model.pojo.Triangle;
import com.besmart.services.TriangleService;
import com.besmart.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = Constants.TRIANGLE)
@Service
public class TriangleResource extends BaseResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleResource.class);

    @Autowired
    TriangleService triangleService;

    /**
     * getEntity
     * http://localhost:8080/triangle222/123
     *
     * @param filter
     * @return
     */
    @PostMapping(value = Constants.FILTER, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getEntitiesByFilter(@RequestBody Filter filter) {
        try {
            List<Triangle> triangles = triangleService.getEntitiesByFilter(filter);
            response = gson.toJson(triangles).toString();
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).append("\n").append(e).toString());
        }
        return response;
    }


    /**
     * getEntity
     * http://localhost:8080/triangle/filter/count
     *
     * @param filter
     * @return
     */
    @PostMapping(value = Constants.FILTER_COUNT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCountOfEntitiesByFilter(@RequestBody Filter filter) {
        try {
            long triangle = triangleService.getCountEntitiesByFilter(filter);
            response = "Count = " + triangle;
            response = gson.toJson(triangle);
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).append("\n").append(e).toString());
        }
        return response;
    }


    /**
     * @param triangle
     * @return
     */
    @PutMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String editEntity(@RequestBody Triangle triangle, @RequestBody GetBy getBy) {
        try {
            boolean isSuccess = triangleService.editEntity(triangle, getBy);
            response = gson.toJson(Constants.IS_ACTION_SUCCESS + isSuccess);
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).append("\n").append(e).toString());
        }
        return response;
    }



    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addEntity(@RequestBody Triangle triangle) {
        try {
            if (triangle == null) {
                LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).append(Constants.ARRIVED_2_TRIANGLE_EDGES_TO_ADD_FIRST).
                        append(triangle.getFirstEdge()).append(Constants.SECOND).append(triangle.getSecondEdge()).toString());
            } else {
                boolean isSuccess = triangleService.addEntity(triangle);
                response = gson.toJson(Constants.IS_ACTION_SUCCESS + isSuccess);
            }
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).append("\n").append(e).toString());
        }
        return response;
    }

}
