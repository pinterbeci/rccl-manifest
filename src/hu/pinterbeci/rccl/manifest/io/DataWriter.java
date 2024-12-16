package hu.pinterbeci.rccl.manifest.io;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import hu.pinterbeci.rccl.manifest.model.Pallet;

public class DataWriter {

    private final List<String> headerNames =
        List.of("PO", "Seal", "Itemnumber", "Description", "Pallet", "Linenumber", "UOM", "TotalPrice", "Weight", "SO",
            "OrderDate", "VesselShortName", "Voyage", "Container", "OrderType", "Bonded", "Department", "POvendor");

    public void dataWriter(final Map<String, Pallet> data) {
        final File csvOutputFile = new File("src/resources/RCCL_manifest_result.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            final String headers = String.join("; ", headerNames);
            pw.println(headers);
            for (Map.Entry<String, Pallet> palletEntry : data.entrySet()) {
                final Pallet pallet = palletEntry.getValue();
                final String palletLineValue = Arrays.stream(pallet.getClass().getDeclaredFields())
                    .map(field -> {
                        field.setAccessible(true);
                        try {
                            Object fieldValue;
                            if (Objects.equals(field.getName(), "orderDate")) {
                                fieldValue = new SimpleDateFormat("yyyyMMdd").format(field.get(pallet));
                            } else {
                                fieldValue = field.get(pallet);
                            }
                            return Objects.nonNull(fieldValue) ? String.valueOf(fieldValue) : "";
                        } catch (final Exception exception) {
                            exception.printStackTrace();
                            return "";
                        }
                    })
                    .collect(Collectors.joining("; "));
                pw.println(palletLineValue);
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}
