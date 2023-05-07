public abstract class BuildInfo extends DefaultTask {

    @Input
    public abstract Property<String> getVersion();

    @OutputFile
    public abstract RegularFileProperty getOutputFile();

    @TaskAction
    public void create() throws IOException {
        Properties prop = new Properties();
        prop.setProperty("version", getVersion().get());
        try (OutputStream output = new FileOutputStream(getOutputFile()
                .getAsFile()
                .get())) {
            prop.store(output, null);
        }
    }
}