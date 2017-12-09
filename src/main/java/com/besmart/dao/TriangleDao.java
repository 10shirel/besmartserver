package com.besmart.dao;

import com.besmart.model.enums.State;
import com.besmart.model.pojo.Triangle;
import com.besmart.storage.StorageFactory;
import com.besmart.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TriangleDao {

    private String id;
    private String firstEdge;
    private String secondEdge;
    private String hypotenuse;

    @Autowired
    Configurations configurations;

    @Autowired
    StorageFactory storageFactory;


    //add
    public boolean addEntity(Triangle triangle) {
        return storageFactory.getStorage().addEntity(triangle);
    }


    //edit

    /**
     * edit entity by id
     *
     * @return
     */
    public boolean editEntityById(Triangle triangle) {
        return storageFactory.getStorage().editEntityById(triangle);
    }

    /**
     * edit entity by State
     *
     * @param triangle
     * @return
     */
    public boolean editEntityByState(Triangle triangle) {
        return storageFactory.getStorage().editEntityByState(triangle);
    }


    /**
     * edit entity by data
     *
     * @param triangle
     * @return
     */
    public boolean editEntityByData(Triangle triangle) {
        return storageFactory.getStorage().editEntityByData(triangle);
    }

    //get

    /**
     * return triangles by ids
     *
     * @param ids
     * @return
     */
    public List<Triangle> getEntitiesByFilter(List<BigInteger> ids) {
        return storageFactory.getStorage().getEntitiesByFilter(ids);
    }

    /**
     * return all triangles
     *
     * @return
     */
    public List<Triangle> getEntitiesByFilter() {
        return storageFactory.getStorage().getEntitiesByFilter();
    }

    /**
     * return triangles by State
     *
     * @param state
     * @return
     */

    public List<Triangle> getEntitiesByFilter(State state) {
        return storageFactory.getStorage().getEntitiesByFilter(state);
    }


    // count

    /**
     * count triangles by ids
     *
     * @param ids
     * @return
     */
    public long getCountEntitiesByFilter(List<BigInteger> ids) {
        return storageFactory.getStorage().getCountEntitiesByFilter(ids);
    }

    /**
     * count all triangles
     *
     * @return
     */
    public long getCountEntitiesByFilter() {
        return storageFactory.getStorage().getCountEntitiesByFilter();
    }

    /**
     * count triangles by State
     *
     * @param state
     * @return
     */

    public long getCountEntitiesByFilter(State state) {
        return storageFactory.getStorage().getCountEntitiesByFilter(state);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstEdge() {
        return firstEdge;
    }

    public void setFirstEdge(String firstEdge) {
        this.firstEdge = firstEdge;
    }

    public String getSecondEdge() {
        return secondEdge;
    }

    public void setSecondEdge(String secondEdge) {
        this.secondEdge = secondEdge;
    }

    public String getHypotenuse() {
        return hypotenuse;
    }

    public void setHypotenuse(String hypotenuse) {
        this.hypotenuse = hypotenuse;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TriangleDao that = (TriangleDao) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstEdge != null ? !firstEdge.equals(that.firstEdge) : that.firstEdge != null) return false;
        if (secondEdge != null ? !secondEdge.equals(that.secondEdge) : that.secondEdge != null) return false;
        return hypotenuse != null ? hypotenuse.equals(that.hypotenuse) : that.hypotenuse == null;
    }


    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstEdge != null ? firstEdge.hashCode() : 0);
        result = 31 * result + (secondEdge != null ? secondEdge.hashCode() : 0);
        result = 31 * result + (hypotenuse != null ? hypotenuse.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TriangleDao{" +
                "id='" + id + '\'' +
                ", firstEdge='" + firstEdge + '\'' +
                ", secondEdge='" + secondEdge + '\'' +
                ", hypotenuse='" + hypotenuse + '\'' +
                '}';
    }
}
