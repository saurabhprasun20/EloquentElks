import {divIcon} from "leaflet";
import {renderToStaticMarkup} from "react-dom/server";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faMapMarker} from '@fortawesome/free-solid-svg-icons'
import {Box, Stack} from "grommet";
import styled from "styled-components";

const Glow = styled.div`
  min-height: 48px;
  min-width: 36px;
  box-shadow: 0 0 60px #ff0000;
  background: rgba(255, 0, 0, 0.20);
`


const airbnbGlowMarkup = renderToStaticMarkup(
        <Stack anchor={'center'} overflow={'visible'}>
            <Glow/>
            <Box margin={{left:'24px'}}>
                <FontAwesomeIcon icon={faMapMarker} size={'4x'} color={'#e74e4e'} />
            </Box>
            <Box align='center' alignSelf='center' margin={{left:'22px', bottom: '10px'}}>
                <FontAwesomeIcon icon={faHome} color={'#ffffff'} size={'2x'}/>
            </Box>
            <Box fill background={'#ff0000'}/>
        </Stack>

);


export const airbnbGlowLeafletIcon = divIcon({
    html: airbnbGlowMarkup,
    iconAnchor: [13, 40]
})
