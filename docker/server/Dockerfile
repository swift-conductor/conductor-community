#
# conductor:server - Combined Swift Conductor Server & UI
#
# ===========================================================================================================
# 0. Builder stage
# ===========================================================================================================
FROM alpine:3.19 AS builder

LABEL maintainer="Swift Software Group <hello@swiftsoftwaregroup.com>"

# ===========================================================================================================
# 0. Build Conductor Server
# ===========================================================================================================


# Install dependencies
RUN apk add openjdk17
RUN apk add git
RUN apk add --update nodejs npm yarn

# Build UI
WORKDIR /

RUN mkdir ui-build
WORKDIR  ui-build

RUN git clone https://github.com/swift-conductor/conductor-ui.git
WORKDIR conductor-ui
RUN ls -ltr

# Include monaco sources into bundle (instead of using CDN)
ENV REACT_APP_MONACO_EDITOR_USING_CDN=false
RUN npm install && cp -r node_modules/monaco-editor public/ && npm run build
RUN echo "Done building UI"

WORKDIR /ui-build
RUN ls -ltr
RUN pwd

# Build community server
WORKDIR /

RUN mkdir server-build
WORKDIR server-build

# Checkout the conductor-community project
RUN git clone https://github.com/swift-conductor/conductor-community.git
WORKDIR conductor-community
RUN ls -ltr

# Build the server
RUN ./gradlew build -x test --stacktrace
RUN echo "Done building server"

WORKDIR /server-build
RUN ls -ltr
RUN pwd


# ===========================================================================================================
# 1. Bin stage
# ===========================================================================================================
FROM alpine:3.19

LABEL maintainer="Swift Software Group <hello@swiftsoftwaregroup.com>"

RUN apk add openjdk17
RUN apk add nginx

# Make app folders
RUN mkdir -p /app/config /app/logs /app/libs

# Copy the compiled output to new image
COPY docker/server/bin /app
COPY docker/server/config /app/config
COPY --from=builder /server-build/conductor-community/community-server/build/libs/*boot*.jar /app/libs/conductor-server.jar

# Copy compiled UI assets to nginx www directory
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=builder /ui-build/conductor-ui/build .

# Copy nginx config
COPY docker/server/nginx/nginx.conf /etc/nginx/http.d/default.conf

# Copy the files for the server into the app folders
RUN chmod +x /app/startup.sh

HEALTHCHECK --interval=60s --timeout=30s --retries=10 CMD curl -I -XGET http://localhost:8080/health || exit 1

CMD [ "/app/startup.sh" ]
ENTRYPOINT [ "/bin/sh"]
