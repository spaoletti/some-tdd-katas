package org.tdd.katas.barcodescanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BarcodeScannerTest {

    BarcodeScanner bs;

    @BeforeEach
    public void beforeEach() {
        Map<String, Double> map = new HashMap<>();
        map.put("12345", 7.25);
        map.put("23456", 12.5);
        bs = new BarcodeScanner(map);
    }

    @Test
    public void barcode_12345_should_display_price_7_25() {
        String price = bs.scan("12345");
        Assertions.assertEquals("$7.25", price);
    }

    @Test
    public void barcode_23456_should_display_price_12_50() {
        String price = bs.scan("23456");
        Assertions.assertEquals("$12.50", price);
    }

    @Test
    public void barcode_99999_should_display_barcode_not_found() {
        String price = bs.scan("99999");
        Assertions.assertEquals("Error: barcode not found", price);
    }

    @Test
    public void empty_barcode_should_display_error_empty_barcode() {
        String price = bs.scan("");
        Assertions.assertEquals("Error: empty barcode", price);
    }

    @Test
    public void should_accept_multiple_barcodes_and_return_the_sum() {
        bs.scan("12345");
        bs.scan("23456");
        Assertions.assertEquals("$19.75", bs.getTotal());
    }

}
