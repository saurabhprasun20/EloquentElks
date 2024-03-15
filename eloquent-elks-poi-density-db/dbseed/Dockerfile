FROM mongo
COPY . .
COPY densities.json /densities.json
RUN ["chmod", "+x", "./scripts/init.sh"]
CMD ./scripts/init.sh