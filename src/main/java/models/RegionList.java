package models;

import java.util.List;

public class RegionList {
    List<Region> data;

    public RegionList(List<Region> data) {
        this.data = data;
    }

    public List<Region> getData() {
        return data;
    }

    public void setData(List<Region> data) {
        this.data = data;
    }
}


