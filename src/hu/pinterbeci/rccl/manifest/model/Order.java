package hu.pinterbeci.rccl.manifest.model;

import java.util.Date;

public class Order {

    private String po;

    private String seal;

    private String itemNumber;

    private String description;

    private String pallet;

    private String lineNumber;

    private String uom;

    private String so;

    private int pieces;

    private double unitCost;

    private double totalPrice;

    private double weight;

    private Date orderDate;

    private String vesselShortname;

    private String voyage;

    private String container;

    private String orderType;

    private String bonded;

    private String department;

    private String poVendor;

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getVesselShortname() {
        return vesselShortname;
    }

    public void setVesselShortname(String vesselShortname) {
        this.vesselShortname = vesselShortname;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBonded() {
        return bonded;
    }

    public void setBonded(String bonded) {
        this.bonded = bonded;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPoVendor() {
        return poVendor;
    }

    public void setPoVendor(String poVendor) {
        this.poVendor = poVendor;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
}
