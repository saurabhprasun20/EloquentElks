import {divIcon} from "leaflet";
import {renderToStaticMarkup} from "react-dom/server";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faCamera, faMapMarker} from '@fortawesome/free-solid-svg-icons'
import {Box, Stack} from "grommet";

const famousMarkup = renderToStaticMarkup(
    <Stack>
        <FontAwesomeIcon icon={faMapMarker} size={'3x'} color={'#52915c'}/>
        <Box align='center' alignSelf='center' margin={{left:'13px', top: '8px'}}>
            <FontAwesomeIcon icon={faCamera} color={'#ffffff'}/>
        </Box>
    </Stack>
);


export const famousLeafletIcon = divIcon({
    html: famousMarkup,
    iconAnchor: [13, 40]
})