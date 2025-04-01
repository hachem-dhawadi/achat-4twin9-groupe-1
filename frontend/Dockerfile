# Stage 1: Build the Angular app
FROM node:18 as build

WORKDIR /app
COPY . .
RUN npm install --legacy-peer-deps
RUN npm run build -- --configuration=production

# Stage 2: Serve the Angular app with http-server
FROM node:18

RUN npm install -g http-server
WORKDIR /app
COPY --from=build /app/dist/achat-frontend .

EXPOSE 8080
CMD ["http-server", "-p", "8080"]
