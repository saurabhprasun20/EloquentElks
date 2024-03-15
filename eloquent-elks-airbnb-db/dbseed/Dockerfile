FROM mongo
COPY . .
COPY airbnb.json /airbnb.json
RUN ["chmod", "+x", "./scripts/init.sh"]
CMD ./scripts/init.sh