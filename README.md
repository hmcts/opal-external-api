# opal-external-api

## Building and deploying the application

### Building the application

#### Environment Variables

The following environment variables are required to run the service.

```bash
OPAL_SOAP_JKS_STRING=/u3+7QAAAAIAAAACAAAAAgADY3BwAAABkVqWGE0ABVguNTA5AAADVDCCA1AwggI4oAMCAQICFFuw3TvZKoraJ5/9So7jAZFalFXYMA0GCSqGSIb3DQEBCwUAMGExCzAJBgNVBAYTAnVrMRcwFQYDVQQIDA5ncmVhdGVyIGxvbmRvbjEPMA0GA1UEBwwGbG9uZG9uMQwwCgYDVQQKDANnb3YxDDAKBgNVBAsMA2NwcDEMMAoGA1UEAwwDY3BwMCAXDTI0MDgxNjA5NDU1NloYDzIxMjQwODE2MDk0NTU2WjBhMQswCQYDVQQGEwJ1azEXMBUGA1UECAwOZ3JlYXRlciBsb25kb24xDzANBgNVBAcMBmxvbmRvbjEMMAoGA1UECgwDZ292MQwwCgYDVQQLDANjcHAxDDAKBgNVBAMMA2NwcDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAJporX4EOXHA2EJsaKO4UBgdidt8oaSIYu0NrGs+KYxMhQkHI6XfT9A8K4YUgC+LBxrr6VEJPkNP2zglLIUOETiI3AVoYu3hGoCGhfEOHt61lCmIItAYtFMQDzBNGUN652gZucIIVVY19Uz1JAqsTYmDL7etFEOMaXQ8L3Y8LTZbxBhsbNaDhUwxHMM7Uz7zTs4tD7Lq0LlflkxexLv1X7GXu71dPlxLh6/z6ccVbqsl7UNfKc+8k8k+JQ0DRXxEyT9QWc8c1Y/iR57yvJeyWg0jLlY+UbHUJxuv0KPusdHSLsq9mD0JMDQwePIZRAISHPvLuCwizXEccZ9ixwxCcRkCAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAEOZGEep4CDOUV9gzfK+YvBC2q2xAOTRMi50n+8gdCJR8xQwh3bQm716ZLwpB23oDskLrTOANlfhpzQ5qlkw8eAKB2u3fbk7vVnwj2Q+1p7Zz4DM7KHoaW6X84tiSP1kjc2+QcumkrNlB//nIRBWLiQvDnMISjFzCSD+3jUGx2UclL2R0YI13Fne+4qI2XRga1MbsmJ9nt8WkD8YFRE4i80VShKtgb+wxwU3mVyLr9btKOqdy6aA+ajT1AofktbaIryATrgFjLX23BHuOfIx/MgxZsYzbD5cPVv5D164FOcOqIwxkoZ3pNWDL/9Moez0LS9P9AbeCuW/Jla0ycLKuaQAAAAEABG9wYWwAAAGRWpMoJQAABQEwggT9MA4GCisGAQQBKgIRAQEFAASCBOloQwjZOoE1fDPxTj2oHW9VxSDgC8vjyN+NQ6vtuZGEp46hvojagP0PV4+cwgWjLXqsMNv1aALHaTkyxngctw8+4s8EM0mwwfvKEC6CRwm0H2INVZHtOk0nSwFLWp+sA4U2JVg1kFjQ5Yj6w1nRO66j02RnanG6roMFHrRPYCayGQ/HJoRQkk27OMN/4YpQoxcAcyD3LiIPwF47bIPy2BtHlMSf4sW+uFcQXSlPHsgDAv5ZufjUkq5MwHz6JEqJEHQcSgO77bRfhAJFgCAEHMJ+AsSQ4j9RjpPLFQGMfp55zVZZlTP+gq0m/AqcNqRNXa8MVYYscveNH4iIVicFcOf4G3Zf7MKFDI+SV12UHuVYEUbtyiTVMhoXtyCaqpRgsPBgqyDFfg2EjjTJOXo7sudLYMRYsPvhLQG6E5IwwnE+PG5R1b2KlqX/IVOTj06qxKD1Bc244QYzmIKaCpK94aSPlSmVOAUYoZ0FnKhW41ED9+zfFZ78339avk5wdyCCj0+Is6VE04RoBJeqDxRH+wKLyHhG72FdVtTGf4afBjFRj/WCezOX1n9zY94Ez05+96M/9NQhzz0GX7kG8fQa7GKO63vG5Cnr1+ZvuBG76/wYPQJM2OxrG8AT8k3cEXqadrpM2nqpYuR+3flagj7PNcTZhbleP+wdlvB042kmOTPwB2um/5Wic5P+tBH0nTTRBxvIMUxepMNBAuwocEW2VsTzvaFX2YY5umodyP9OmpAHxGtnSyqunW05DA8N4L3HXx5PNmyU5j20LyAmUcc62bghpbYytCNhKLCA6LBBE+ahkB+7BXLTDEWjK5yBvndaat3OCNulrU+MgvDUjfBu4zlwZ8MRneksXVkIvDzQx4tXKca6nyyDyJX5ggJNrK8vgnYVvd3QWSaulXTZZmLIa5IL7SOK0/aNWjzCowPjlmLOQFchkD5OZlSQLYhj6ZA5olwMlqA8/z41A80sw8GsGjJ0Z3SKYARgJNGdW4cA4pDE7s96jSNHvcRU3//pn0f3EivLd6zJd8egi07vSlzUoo5C3oHeCoaRNaRJr3f4Byen58h8tlJ80WugRdCmOektOoV4zIovgZqIJ0RK40VbiMpXVr75E5EfIBsrk7P90UoWQuv+0ocW2MlRmPXzAFH3Lrb77ot5bLtYtA7xW20wkGSMNRK54PhXTED3NVTHsU8QfTTfu1l/pIeIxU9NIbQ4fTVeWmfrkikT+/Ovc+qAtNg9txkOiiyFWls6sIjteMci5EWO4aPYNuZvHk8qGB6oCeCTpPow23nus2N2pRDfR8miWrHBMwrwOqGGjFISEi5kIo9yQarA/0hPGw7eyq+ZhN8WqwFQXT+0A46golA50cNiM5slAULMqHPJwlHlD6A5fXoTegvnT2HS5MIeVA9ti1c8OMhJ8QyRYkKPDouftxP0UJr1KtgHaL92zV/76XjF460R7cBHktcF5BpF4qGghbzcarsZtpl38f+KKzqNcFdCDSgdyhKZNzgrRzldFtcym/HZlAwzrCkoVoi4zk2xUg4Dpcm1sXmtXN7x0/OBFM4DHfLw5FlApfp20AIVYZ73nPlYwQFlWAYNfSRgMwyPRFH6ImqYOlTAPwhTTjLI7JQ2fP5hTqL4B9Bo0tGBbGoEvc2D0/iIgKdPtEHrGViB3WQP50KHUOC46DYAAAABAAVYLjUwOQAAA1wwggNYMIICQKADAgECAhRtzP3mGuh0ps4xh7R2AAGRWpHxxzANBgkqhkiG9w0BAQsFADBmMQswCQYDVQQGEwJ1azEXMBUGA1UECAwOZ3JlYXRlciBsb25kb24xDzANBgNVBAcMBmxvbmRvbjEMMAoGA1UECgwDY2dpMRAwDgYDVQQLDAdqdXN0aWNlMQ0wCwYDVQQDDARvcGFsMB4XDTI0MDgxNjA5NDMxOVoXDTI1MDgxNjA5NDMxOVowZjELMAkGA1UEBhMCdWsxFzAVBgNVBAgMDmdyZWF0ZXIgbG9uZG9uMQ8wDQYDVQQHDAZsb25kb24xDDAKBgNVBAoMA2NnaTEQMA4GA1UECwwHanVzdGljZTENMAsGA1UEAwwEb3BhbDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAOiftyQVAFHo79SjtPLRifMQm115MsOPAOZA977Eqk+wqHwJMyOCt8dISepUjbMV8SgNojijrW7aic0pWmyLz1Eiagbffh2BAuzxiUdlb+C6Whzvp1X24IFN4InAwN4CH/xE3hrEr/f3UMasCi6TTyIwrCGkyKZkXjSCq2kM6KlPzBr9M6zMhHgx3lvV/VvzyAqCWnLZodoaAeKKmUkGYHlptxLzS0SVFTRkePgZbotUdX71DW6U2Acq557T+4mxAte1VUYGIjwWRgayLWWsoTFUHM1bAcLS2C7H2/HhjXIeXVDEffRM1lf6wtNUajfeI3BWx7c9hNlB8YOlto65K+UCAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAQTnEWNeRme/U+4quxMqhtkO7Y4sfwVS1YhOSQG9gX1dfkUN8UlAxreY+8d4Dq7pWfmPGKsfJXrBOTrgGqm+5HUm+L0nxDy8nM7hlbvB7AJ1nTn+eHqp/USU2h6MZU7oXKemfDQPwEm62rJO1ZqeRXygGVZ35upMq0PT7XTLBd8CnDUvzT+fyBSBsaPp2IxnkO9eoyFE81Fj6Om+URk7Vk009tSfY6rRSG9SRb9rb7o2Eew25NCV9Dl4WKtE00Inei3mehJRM/svQbocLGBKIfu/C3/gI3aFlYymUYLAvNg2Yvyb9j8xLrfb2wnXULrSUobHQpBZHKkmb1eOz+WlMOSoROnbn5CulVRfklYsACAKQDFVI
OPAL_SOAP_JKS_PASSWORD=password
```
The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.



To build the project execute the following command:

```bash
  ./gradlew build
```

### Running the application

Create the image of the application by executing the following command:

```bash
  ./gradlew assemble
```

Create docker image:

```bash
  docker-compose build
```

Run the distribution (created in `build/install/opal-external-api` directory)
by executing the following command:

```bash
  docker-compose up
```

This will start the API container exposing the application's port
(set to `4551` in this template app).

In order to test if the application is up, you can call its health endpoint:

```bash
  curl http://localhost:4551/health
```

You should get a response similar to this:

```
  {"status":"UP","diskSpace":{"status":"UP","total":249644974080,"free":137188298752,"threshold":10485760}}
```

### Alternative script to run application

To skip all the setting up and building, just execute the following command:

```bash
./bin/run-in-docker.sh
```

For more information:

```bash
./bin/run-in-docker.sh -h
```

Script includes bare minimum environment variables necessary to start api instance. Whenever any variable is changed or any other script regarding docker image/container build, the suggested way to ensure all is cleaned up properly is by this command:

```bash
docker-compose rm
```

It clears stopped containers correctly. Might consider removing clutter of images too, especially the ones fiddled with:

```bash
docker images

docker image rm <image-id>
```

There is no need to remove postgres and java or similar core images.

## Testing
All testing of the SOAP interfaces can be done via opensource tool SoapUI (https://www.soapui.org/downloads/soapui/).
A pre-prepared SoapUI project is available in the /soapui directory. Import this project into SoapUI and run the tests.

### CPP Services
For CPP services SoapUI will need to be provided a test jks file. This can be found in the /soapui/testKeystore directory.
Within SoapUI, navigate to the CPP-OPAL project and update the WS-Security Configurations > Keystore settings to point to the provided jks file. (remove any existing keystore configurations)
The password for the jks file is 'password'.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

