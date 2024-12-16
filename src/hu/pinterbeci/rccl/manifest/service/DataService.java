package hu.pinterbeci.rccl.manifest.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import hu.pinterbeci.rccl.manifest.model.Order;
import hu.pinterbeci.rccl.manifest.model.Pallet;

public class DataService {

    private Order order;

    private final Map<String, Pallet> pallets = new HashMap<>();

    public Map<String, Pallet> getPallets() {
        return pallets;
    }

    public void handleReadData(final String[] partOfReadLine) {
        try {
            if (partOfReadLine.length != 20) {
                return;
            }
            final String orderType = partOfReadLine[16];
            if (!Objects.equals(orderType, "OM")) {
                return;
            }
            fillOrderFromReadData(partOfReadLine);
            constructNewPallet(order);
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    private void fillOrderFromReadData(final String[] partOfReadLine) throws Exception {
        final String po = partOfReadLine[0];
        final String seal = partOfReadLine[1];
        final String itemNumber = partOfReadLine[2];
        final String description = partOfReadLine[3];
        final String lineNumber = partOfReadLine[5];
        final String uom = partOfReadLine[6];
        final double pieces = Double.parseDouble(partOfReadLine[7]);
        final double unitCost = Double.parseDouble(partOfReadLine[8]);
        final String so = partOfReadLine[11];
        final Date orderDate = new SimpleDateFormat("yyyyMMdd").parse(partOfReadLine[12]);
        final String vesselShortName = partOfReadLine[13];
        final String voyage = partOfReadLine[14];
        final String container = partOfReadLine[15];
        final String bonded = partOfReadLine[17];
        final String department = partOfReadLine[18];
        final String poVendor = partOfReadLine[19];


        final String pallet = partOfReadLine[4];
        final double totalPrice = Double.parseDouble(partOfReadLine[9]);
        final double weight = Double.parseDouble(partOfReadLine[10]);

        this.order = new Order();
        this.order.setBonded(bonded);
        this.order.setContainer(container);
        this.order.setDepartment(department);
        this.order.setOrderType(partOfReadLine[16]);
        this.order.setDescription(description);
        this.order.setOrderDate(orderDate);
        this.order.setItemNumber(itemNumber);
        this.order.setPo(po);
        this.order.setSeal(seal);
        this.order.setLineNumber(lineNumber);
        this.order.setUom(uom);
        this.order.setSo(so);
        this.order.setVesselShortname(vesselShortName);
        this.order.setVoyage(voyage);
        this.order.setPoVendor(poVendor);
        this.order.setTotalPrice(totalPrice);
        this.order.setWeight(weight);
        this.order.setPallet(pallet);
        this.order.setPieces((int) pieces);
        this.order.setUnitCost(unitCost);
    }

    private void constructNewPallet(final Order order) {

        if (pallets.containsKey(order.getPallet())) {
            final Pallet pallet = pallets.get(order.getPallet());
            if (order.getOrderDate().after(pallet.getOrderDate())) {

                pallet.setOrderDate(order.getOrderDate());
            }

            pallet.setTotalPrice(pallet.getTotalPrice() + order.getTotalPrice());
            pallet.setWeight(pallet.getWeight() + order.getWeight());
            pallets.put(order.getPallet(), pallet);
            return;
        }
        pallets.put(order.getPallet(), constructNewPalletFromOrder(order));
    }

    private Pallet constructNewPalletFromOrder(final Order order) {
        final Pallet pallet = new Pallet();
        pallet.setBonded(order.getBonded());
        pallet.setContainer(order.getContainer());
        pallet.setDepartment(order.getDepartment());
        pallet.setOrderType(order.getOrderType());
        pallet.setDescription(order.getDescription());

        pallet.setOrderDate(order.getOrderDate());
        pallet.setItemNumber(order.getItemNumber());
        pallet.setPo(order.getPo());
        pallet.setSeal(order.getSeal());
        pallet.setLineNumber(order.getLineNumber());
        pallet.setUom(order.getUom());
        pallet.setSo(order.getSo());
        pallet.setVesselShortname(order.getVesselShortname());
        pallet.setVoyage(order.getVoyage());
        pallet.setPoVendor(order.getPoVendor());
        pallet.setTotalPrice(order.getPieces() * order.getUnitCost());
        pallet.setWeight(order.getWeight());
        pallet.setName(order.getPallet());

        return pallet;
    }
}
