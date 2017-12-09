package com.besmart.services;

import com.besmart.dao.TriangleDao;
import com.besmart.model.Filter;
import com.besmart.model.enums.GetBy;
import com.besmart.model.enums.State;
import com.besmart.model.pojo.Triangle;
import com.besmart.utils.Configurations;
import com.besmart.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by shirela on 12/7/2017.
 */
@Service
public class TriangleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TriangleService.class);

    @Autowired
    protected ConfigurableApplicationContext appContext;

    @Autowired
    protected TriangleDao triangleDao;

    @Autowired
    protected Configurations configurations;

    //get

    /**
     * @param filter
     * @return
     */
    public List<Triangle> getEntitiesByFilter(Filter filter) {
        List<Triangle> triangles = new ArrayList<>();
        try {
            if (filter != null) {
                List<BigInteger> ids = filter.getIds();
                State state = filter.getState();
                GetBy getBy = filter.getGetBy();

                if (GetBy.IDS.equals(getBy)) {
                    triangles = triangleDao.getEntitiesByFilter(ids);
                } else if (GetBy.ALL.equals(getBy)) {
                    triangles = triangleDao.getEntitiesByFilter();
                } else if (GetBy.STATE.equals(getBy)) {
                    triangles = triangleDao.getEntitiesByFilter(state);
                }
            } else {
                LOGGER.error(new StringBuilder().append(new Date()).append(Constants.DATA_WAS_SENT_NULL_OR_EMPTY).toString());
            }
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());

        }

        return triangles;
    }


    //count

    /**
     * @param filter
     * @return
     */
    public long getCountEntitiesByFilter(Filter filter) {
        long countEntities = -1;
        try {
            if (filter != null && filter.getGetBy() != null) {
                List<BigInteger> ids = filter.getIds();
                State state = filter.getState();
                GetBy getBy = filter.getGetBy();

                if (GetBy.IDS.equals(getBy)) {
                    countEntities = triangleDao.getCountEntitiesByFilter(ids);
                } else if (GetBy.ALL.equals(getBy)) {
                    countEntities = triangleDao.getCountEntitiesByFilter();
                } else if (GetBy.STATE.equals(state)) {
                    countEntities = triangleDao.getCountEntitiesByFilter(state);
                }
            } else {
                LOGGER.error(new StringBuilder().append(new Date()).append(Constants.DATA_WAS_SENT_NULL_OR_EMPTY).toString());
            }
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());

        }
        return countEntities;
    }

    //edit

    /**
     * @param triangles
     * @return
     */
    public boolean editEntity(Triangle triangle, GetBy getBy) {
        boolean isSuccess = false;
        try {
            if (triangle != null) {

                if (GetBy.IDS.equals(getBy)) {
                    isSuccess = triangleDao.editEntityById(triangle);
                } else if (GetBy.STATE.equals(getBy)) {
                    isSuccess = triangleDao.editEntityByState(triangle);
                } else if (GetBy.DATA.equals(getBy)) {
                    isSuccess = triangleDao.editEntityByData(triangle);
                }
            } else {
                LOGGER.error(new StringBuilder().append(new Date()).append(Constants.DATA_WAS_SENT_NULL_OR_EMPTY).toString());
            }
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());

        }
        return isSuccess;
    }


    //add

    /**
     * @param triangle
     * @return
     */
    public boolean addEntity(Triangle triangle) {
        boolean isSuccess = false;
        try {
            if (triangle == null) {
                LOGGER.error(new StringBuilder().append(new Date()).append(Constants.TRIANGLE_EDGES_IS_NULL).toString());
                return false;
            } else {
                LOGGER.debug(new StringBuilder().append(new Date()).append(Constants.ADD_ENTITY_ARRIVED_2_TRIANGLE_EDGES_TO_ADD).append("\n").append(Constants.TRIANGLE_ID).
                        append(triangle.getId()).append(Constants.FIRST).append(triangle.getFirstEdge()).append(Constants.SECOND).append(triangle.getSecondEdge()).toString());
            }
            isSuccess = triangleDao.addEntity(triangle);
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());
        }
        return isSuccess;
    }


}
