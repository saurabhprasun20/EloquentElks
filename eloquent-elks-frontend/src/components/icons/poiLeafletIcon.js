import {divIcon} from "leaflet";
import {renderToStaticMarkup} from "react-dom/server";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMapMarker} from '@fortawesome/free-solid-svg-icons'
import {Box, Stack} from "grommet";

export function poiLeafletIcon(icon, poi) {
    let markerColor = '#6e6ddb'
    if (poi.type === 'restaurant') {
        markerColor = '#88d7c0'
    }
    if (poi.type === 'bar') {
        markerColor = '#88d793'
    }

    if (poi.type === 'cafe') {
        markerColor = '#b0d788'
    }

    const poiMarkup = renderToStaticMarkup(
        <Stack>
            <FontAwesomeIcon icon={faMapMarker} size={'3x'} color={markerColor}/>
            <Box align='center' alignSelf='center' margin={{left:'13px', top: '8px'}}>
                {icon}
            </Box>
        </Stack>
    );

    return divIcon({
        html: poiMarkup,
        iconAnchor: [13, 40]
    })
}
