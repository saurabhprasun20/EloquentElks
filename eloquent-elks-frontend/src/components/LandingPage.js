import {Box, Button, Grid, Layer, Text} from "grommet";
import React, {useState} from "react";
import {getAttractionType, getAttractionTypes} from "../resources/attractions";


export const LandingPage = props => {

    const [localAttractionTypes, setLocalAttractionTypes] = useState([]);

    let attractions = getAttractionTypes("white", "50");
    let numberOfAttractionTypes = attractions.length;
    let colsAndRows = Math.ceil(Math.sqrt(numberOfAttractionTypes));
    let rows = Array(colsAndRows).fill("xsmall");
    let cols = Array(colsAndRows).fill("small");


    const closeLandingPage = async () => {
        props.setShowLanding(false);
        props.setAttractionTypes(localAttractionTypes);
        props.onLoadRecommendation(localAttractionTypes);
    };

    const isSelected = (value) => {
        return localAttractionTypes.indexOf(value) >= 0;
    }

    const mergeAttractionTypes = (attractionType) => {
        let currentAttractionTypes = [...localAttractionTypes];

        let indexOfElement = currentAttractionTypes.indexOf(attractionType);

        if (indexOfElement >= 0) {
            currentAttractionTypes.splice(indexOfElement, 1);
        } else {
            currentAttractionTypes.push(attractionType);
        }

        setLocalAttractionTypes(currentAttractionTypes);
    }

    return (
        <Layer
            onClickOutside={closeLandingPage}
            onEsc={closeLandingPage}
        >
            <Box flex align='start' justify='center' pad='large'>
                <Text size="xlarge">Welcome to NYC! Please select your preferred types of attractions.</Text>
            </Box>
            <Box flex align='center' justify='center' pad='large'>
                <Grid
                    gap="small"
                    columns={cols}
                    rows={rows}
                    key={localAttractionTypes.flat().toString()}
                >
                    {attractions.map((attr, i) =>
                        <Button key={i}
                                primary={isSelected(attr.value)}
                                onClick={() => mergeAttractionTypes(attr.value)}
                                icon={getAttractionType(attr.key, isSelected(attr.value) ? "white" : "black", "50").icon}
                                label={attr.caption}/>
                    )}
                </Grid>
            </Box>
            <Box flex align='stretch' justify='center' pad='large'>
                <Button primary label="Save" onClick={closeLandingPage}/>
            </Box>
        </Layer>
    )
}

