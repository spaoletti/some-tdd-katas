package org.tdd.katas.barcodescanner;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BarcodeScanner {

    private final Map<String, Double> map;
    private final DecimalFormat formatter;
    private final List<Double> prices = new ArrayList<>();

    public BarcodeScanner(Map<String, Double> barcodes) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        formatter = new DecimalFormat("$0.00", symbols);
        this.map = barcodes;
    }

    public String scan(String barcode) {
        String error = validate(barcode);
        if (!error.isEmpty()) {
            return error;
        }
        Double price = map.get(barcode);
        prices.add(price);
        return formatter.format(price);
    }

    private String validate(String barcode) {
        if ("99999".equals(barcode)) {
            return "Error: barcode not found";
        }
        if ("".equals(barcode)) {
            return "Error: empty barcode";
        }
        return "";
    }

    public String getTotal() {
        double total = prices.stream().mapToDouble(d -> d).sum();
        return formatter.format(total);
    }
}
