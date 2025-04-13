# Base image
FROM nginx:alpine

# Copy the built Angular app into the Nginx HTML folder
COPY dist/achat-frontend /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]