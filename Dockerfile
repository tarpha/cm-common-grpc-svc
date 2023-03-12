FROM gradle:7.6-jdk17
RUN mkdir /cm-common-grpc-svc
COPY . /cm-common-grpc-svc
WORKDIR /cm-common-grpc-svc
RUN gradle clean build

FROM openjdk:17-alpine
COPY --from=0 /cm-common-grpc-svc/build/libs/cm-common-grpc-svc-*.jar cm-common-grpc-svc.jar
EXPOSE 50051
ENTRYPOINT [ "java", "-jar", "cm-common-grpc-svc.jar" ]