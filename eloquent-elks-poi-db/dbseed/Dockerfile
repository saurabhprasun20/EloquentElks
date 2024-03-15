FROM mongo
COPY . .
COPY poi.json /poi.json
COPY famous.json /famous.json
RUN ["chmod", "+x", "./scripts/init.sh"]
CMD ./scripts/init.sh