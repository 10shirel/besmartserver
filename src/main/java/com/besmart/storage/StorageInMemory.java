package com.besmart.storage;

import com.besmart.model.enums.State;
import com.besmart.model.pojo.Triangle;
import com.besmart.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class StorageInMemory implements StorageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageInMemory.class);

    private ConcurrentHashMap<String, Triangle> idToTriangle = new ConcurrentHashMap<>();
    private ConcurrentSkipListSet<String> preCalcList = new ConcurrentSkipListSet();
    private ConcurrentSkipListSet<String> postCalcList = new ConcurrentSkipListSet();
    private ConcurrentHashMap<String, Triangle> isAlreadyChange = new ConcurrentHashMap<>();


//edit

    /**
     * edit entity by id
     *
     * @param triangle
     * @return
     */
    @Override
    public boolean editEntityById(Triangle triangle) {
        boolean isSuccess;
        try {
            synchronized (StorageInMemory.class) {
                Triangle oldTriangle = idToTriangle.get(triangle.getId());
                printBeforeAfterChnage(triangle, oldTriangle);
                idToTriangle.put(triangle.getId(), triangle);
            }

            isSuccess = true;
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.ADD_ENTITY_TRIANGLE_WAS_NOT_SAVED).append("\n").
                    append(Constants.TRIANGLE_ID).append(triangle.getId()).toString());
            isSuccess = false;
        }
        return isSuccess;
    }

    private void printBeforeAfterChnage(Triangle triangle, Triangle oldTriangle) {
        LOGGER.debug(new StringBuilder().append(new Date()).append(Constants.TRIANGLE_ID_BEFORE).
                append(oldTriangle.getId()).
                append(Constants.FIRST_EDGE).
                append(oldTriangle.getFirstEdge()).
                append(Constants.SECOND_EDGE).
                append(oldTriangle.getSecondEdge()).
                append(Constants.HYPOTENUSE).
                append(oldTriangle.getHypotenuse()).
                append(Constants.STATE).
                append(oldTriangle.getState()).
                toString());

        LOGGER.debug(new StringBuilder().append(new Date()).append(Constants.TRIANGLE_ID_AFTER).
                append(triangle.getId()).
                append(Constants.FIRST_EDGE).
                append(triangle.getFirstEdge()).
                append(Constants.SECOND_EDGE).
                append(triangle.getSecondEdge()).
                append(Constants.HYPOTENUSE).
                append(triangle.getHypotenuse()).
                append(Constants.STATE).
                append(triangle.getState()).
                toString());
    }

    /**
     * edit entity by State
     *
     * @param triangle
     * @return
     */
    @Override
    public boolean editEntityByState(Triangle triangle) {
        boolean isSuccess;
        try {
            synchronized (StorageInMemory.class) {
                if (triangle != null && triangle.getState() != null) {
                    if (State.PRECALC.equals(triangle.getState())) {
                        preCalcList.stream().forEach(current -> editEntityById(triangle));
                    } else if (State.POSTCALC.equals(triangle.getState())) {
                        postCalcList.stream().forEach(current -> editEntityById(triangle));
                    }
                }
                idToTriangle.put(triangle.getId(), triangle);
            }
            isSuccess = true;
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.ADD_ENTITY_TRIANGLE_WAS_NOT_SAVED).append("\n").
                    append(Constants.TRIANGLE_ID).append(triangle.getId()).toString());
            isSuccess = false;
        }
        return isSuccess;
    }


    /**
     * edit entity by data
     *
     * @param triangle
     * @return
     */
    @Override
    public boolean editEntityByData(Triangle triangle) {
        return false;
    }


    //add
    @Override
    public boolean addEntity(Triangle triangle) {
        boolean isSuccess;
        try {
            savePrecOrPostCalcIds(triangle);
            idToTriangle.put(triangle.getId(), triangle);
            isSuccess = true;
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.ADD_ENTITY_TRIANGLE_WAS_NOT_SAVED).append("\n").
                    append(Constants.TRIANGLE_ID).append(triangle.getId()).toString());
            isSuccess = false;
        }
        return isSuccess;
    }

    //get

    /**
     * return triangles by ids
     *
     * @param ids
     * @return
     */
    @Override
    public List<Triangle> getEntitiesByFilter(List<BigInteger> ids) {
        List<Triangle> triangles = new ArrayList<>();
        try {
            if (ids != null && !ids.isEmpty()) {
                ids.stream().forEach(current -> triangles.add(idToTriangle.get(current)));
            } else {
                LOGGER.error(new StringBuilder().append(new Date()).append(Constants.DATA_WAS_SENT_NULL_OR_EMPTY).toString());
            }
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());
        }
        return triangles;
    }

    /**
     * return all triangles
     *
     * @return
     */
    @Override
    public List<Triangle> getEntitiesByFilter() {
        List<Triangle> triangles = new ArrayList<>();
        try {

            idToTriangle.forEach((k, v) -> triangles.add(v));
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());

        }
        return triangles;
    }

    /**
     * return triangles by State
     *
     * @param state
     * @return
     */

    @Override
    public List<Triangle> getEntitiesByFilter(State state) {
        List<Triangle> triangles = new ArrayList<>();
        try {

            if (state != null) {
                synchronized (StorageInMemory.class) {
                    if (State.PRECALC.equals(state)) {
                        preCalcList.stream().forEach(current -> triangles.add(idToTriangle.get(current)));
                    } else if (State.POSTCALC.equals(state)) {
                        postCalcList.stream().forEach(current -> triangles.add(idToTriangle.get(current)));
                    }
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
     * count triangles by ids
     *
     * @param ids
     * @return
     */
    @Override
    public long getCountEntitiesByFilter(List<BigInteger> ids) {
        long size = -1;
        try {
            size = ids.size();
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());
        }
        return size;
    }


    /**
     * count all triangles
     *
     * @param
     * @return
     */
    @Override
    public long getCountEntitiesByFilter() {
        long size = -1;
        try {
            size = idToTriangle.size();
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());
        }
        return size;
    }


    /**
     * count triangles by State
     *
     * @param
     * @return
     */
    @Override
    public long getCountEntitiesByFilter(State state) {
        long size = -1;
        try {
            if (state != null) {
                if (State.PRECALC.equals(state)) {
                    size = preCalcList.size();
                } else if (State.POSTCALC.equals(state)) {
                    size = postCalcList.size();
                }
            } else {
                LOGGER.error(new StringBuilder().append(new Date()).append(Constants.DATA_WAS_SENT_NULL_OR_EMPTY).toString());
            }
        } catch (Exception e) {
            LOGGER.error(new StringBuilder().append(new Date()).append(Constants.SOMETHING_IS_WRONG_PLEASE_TRY_LATER).toString());

        }
        return size;
    }

    /**
     * Save pre calc/ post calc in different lists
     *
     * @param triangle
     */
    private void savePrecOrPostCalcIds(Triangle triangle) {
        if (triangle.getState() != null) {
            if (triangle.getState().equals(State.PRECALC)) {
                preCalcList.add(triangle.getId());//save triangleId to prepare fast load in 'get' action
            } else if (triangle.getState().equals(State.POSTCALC)) {
                postCalcList.add(triangle.getId());//save triangleId to prepare fast load in 'get' action
            }
        }
    }

    public ConcurrentHashMap<String, Triangle> getIdToTriangle() {
        return idToTriangle;
    }

    public ConcurrentSkipListSet<String> getPreCalcList() {
        return preCalcList;
    }

    public ConcurrentSkipListSet<String> getPostCalcList() {
        return postCalcList;
    }
}
