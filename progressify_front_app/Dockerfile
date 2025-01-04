FROM node:20.11.0
WORKDIR /app
COPY progressify_front_app/ ./
RUN npm ci
COPY . .
EXPOSE 5174
CMD ["npm", "run", "build"]