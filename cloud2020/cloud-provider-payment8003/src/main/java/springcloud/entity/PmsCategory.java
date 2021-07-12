package springcloud.entity;

import java.util.List;

public class PmsCategory {
    private Long menueId;
    private String name;
    private Long parentCid;
    private int catLevel;
    private int showStatus;
    private String sort;
    private String icon;
    private String productUnit;
    private int productCount;
    // 子菜单
    private List<PmsCategory> children;

    public List<PmsCategory> getChildren() {
        return children;
    }

    public void setChildren(List<PmsCategory> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PmsCategory{" +
                "menueId=" + menueId +
                ", name='" + name + '\'' +
                ", parentCid=" + parentCid +
                ", catLevel=" + catLevel +
                ", showStatus=" + showStatus +
                ", sort='" + sort + '\'' +
                ", icon='" + icon + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productCount=" + productCount +
                '}';
    }

    public Long getMenueId() {
        return menueId;
    }

    public void setMenueId(Long menueId) {
        this.menueId = menueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCid() {
        return parentCid;
    }

    public void setParentCid(Long parentCid) {
        this.parentCid = parentCid;
    }

    public int getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(int catLevel) {
        this.catLevel = catLevel;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
