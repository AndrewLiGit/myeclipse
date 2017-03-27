package com.augmentum.run;

import com.augmentum.excel.Exporter;

public class AudienceComponentExportRunner {

    public static void run() {
        new Exporter().export();
    }
    
    public static void main(String[] args) throws Exception {
        run();
    }
    
}
