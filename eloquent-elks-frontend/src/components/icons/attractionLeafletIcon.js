import {divIcon} from "leaflet";
import {renderToStaticMarkup} from "react-dom/server";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArchway, faMapMarker} from '@fortawesome/free-solid-svg-icons'
import {Box, Stack} from "grommet";

const attractionMarkup = renderToStaticMarkup(
    <Stack>
        <FontAwesomeIcon icon={faMapMarker} size={'3x'} color={'#5755e3'}/>
        <Box align='center' alignSelf='center' margin={{left:'13px', top: '8px'}}>
            <FontAwesomeIcon icon={faArchway} color={'#ffffff'}/>
        </Box>
    </Stack>
);


export const attractionLeafletIcon = divIcon({
    html: attractionMarkup,
    iconAnchor: [13, 40]
})

