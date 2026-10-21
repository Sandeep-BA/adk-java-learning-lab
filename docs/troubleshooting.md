# Troubleshooting

By Sandeep Belgavi.

## Maven or Java is not found

Install JDK 17+ and Maven 3.9+, reopen the terminal and run `java -version` and `mvn -version`. Maven's reported Java home is the one used to compile this project. Run commands from the repository root and keep `-f java/pom.xml`.

## Dependency downloads fail

The first build needs access to Maven Central. Check connectivity and your organization's approved proxy configuration. Do not disable TLS validation or paste credentials into the POM. The offline model does not remove the initial dependency-download requirement.

## The prompt is split into several arguments

Keep the complete Maven property quoted, including the inner single quotes around the prompt:

```sh
mvn -f java/pom.xml exec:java "-Dexec.args=2 offline 'Look up SUP-1043'"
```

## SLF4J provider warning appears

The sample has no logging backend. The warning is expected and does not mean the tests failed. In a larger application, deliberately configure a backend and redaction policy. Do not hide unrelated errors along with this warning.

## Live mode rejects configuration

Set both environment variables described in [live mode](live-mode.md). A `.env` file alone is not loaded. Do not switch silently to offline mode and claim the provider worked.

## A test fails after a change

Read the assertion and the smallest relevant method. Revert or fix the behavior rather than weakening the assertion. Share only synthetic inputs and a redacted error report when asking for help.
