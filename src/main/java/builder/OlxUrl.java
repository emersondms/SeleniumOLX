package builder;

public class OlxUrl {

    private final String stateUrl;
    private final String query;

    private OlxUrl(Builder builder) {
        this.stateUrl = builder.stateUrl;
        this.query = builder.query;
    }

    public static class Builder {

        private String stateUrl;
        private String query;

        public Builder stateUrl(String stateUrl) {
            this.stateUrl = stateUrl;
            return this;
        }

        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public String build() {
            if (this.stateUrl != null) {
                return new StringBuilder()
                    .append(this.stateUrl)
                    .append("/veiculos?q=")
                    .append(this.query)
                    .toString()
                    .replace(" ", "%20");
            } else {
                return new StringBuilder()
                    .append("http://www.olx.com.br")
                    .append("/veiculos?q=")
                    .append(this.query)
                    .toString()
                    .replace(" ", "%20");
            }
        }
    }
}
