FROM node:20.11.0
WORKDIR /app
COPY progressify_front_app/ ./
RUN npm ci
COPY . .
EXPOSE 3000
CMD ["npm", "start"]