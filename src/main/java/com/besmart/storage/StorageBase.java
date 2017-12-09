package com.besmart.storage;

import com.besmart.model.enums.State;
import com.besmart.model.pojo.Triangle;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface StorageBase {

    //add
    default public boolean addEntity(Triangle triangle) {
        return false;
    }

    ;

    //edit

    /**
     * edit entity by id
     *
     * @return
     */
    default public boolean editEntityById(Triangle triangle) {
        return false;
    }

    /**
     * edit entity by State
     *
     * @param triangle
     * @return
     */
    default public boolean editEntityByState(Triangle triangle) {
        return false;
    }


    /**
     * edit entity by data
     *
     * @param triangle
     * @return
     */
    default public boolean editEntityByData(Triangle triangle) {
        return false;
    }

    //get

    /**
     * return triangles by ids
     *
     * @param ids
     * @return
     */
    default public List<Triangle> getEntitiesByFilter(List<BigInteger> ids) {
        return null;
    }

    /**
     * return all triangles
     *
     * @return
     */
    default public List<Triangle> getEntitiesByFilter() {
        return null;
    }

    /**
     * return triangles by State
     *
     * @param state
     * @return
     */

    default public List<Triangle> getEntitiesByFilter(State state) {
        return null;
    }

    /**
     * count triangles by ids
     *
     * @param ids
     * @return
     */
    default public long getCountEntitiesByFilter(List<BigInteger> ids) {
        return 0;
    }

    ;

    /**
     * count all triangles
     *
     * @param
     * @return
     */
    default public long getCountEntitiesByFilter() {
        return 0;
    }

    ;

    /**
     * return triangles by State
     *
     * @param
     * @return
     */
    default public long getCountEntitiesByFilter(State state) {
        return 0;
    }

    ;

}
