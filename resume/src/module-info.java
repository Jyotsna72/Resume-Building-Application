module resume {
    requires java.desktop; // Required for Swing and AWT

    // Exporting your packages
    exports com.resumebuilder.gui;
    exports com.resumebuilder.model;
    exports com.resumebuilder.util;
}
