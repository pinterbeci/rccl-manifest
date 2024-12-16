package hu.pinterbeci.rccl.manifest.io;

import java.io.BufferedReader;
import java.io.FileReader;

import hu.pinterbeci.rccl.manifest.service.DataService;

public class IOHandler {

    private static final String FILE_PATH = "src/resources/RCCL_manifest_non_formatted.csv";

    private static DataService dataService;

    public static void prepareResult() {
        initDependency();
        try (final BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                final String[] partOfTheLine = line.split(",");
                dataService.handleReadData(partOfTheLine);
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        } finally {
            new DataWriter().dataWriter(dataService.getPallets());
        }
    }

    private static void initDependency() {
        dataService = new DataService();
    }
}
