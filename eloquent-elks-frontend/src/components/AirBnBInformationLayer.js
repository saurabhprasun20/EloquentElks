import React, {useState} from 'react'
import {
    Anchor,
    Box,
    Button,
    Card,
    CardBody,
    CardFooter,
    Collapsible,
    Heading,
    Image,
    Layer, List, Markdown,
    Paragraph,
    Stack
} from "grommet";
import {Close, Favorite, FormDown, FormUp, Home, Money, ShareOption, User} from "grommet-icons";
import {getAptImage} from "../resources/getAptImage";


const RoomInformation = props => {
    return (
        <Box margin={{bottom: 'small'}} direction={"row"} gap={'xsmall'} align={'center'}>
            {props.icon}
            <Markdown>{props.roomInformation}</Markdown>
        </Box>
    )
};

export const AirBnBInformationLayer = props => {
    const [open, setOpen] = React.useState(false);
    const [favorite, setFavorite] = useState(false);


    const ExpandButton = ({...rest}) => {
        const Icon = open ? FormUp : FormDown;
        return (
            <Button
                hoverIndicator="light-4"
                icon={<Icon color="brand"/>}
                {...rest}
            />
        );
    };

    let image = getAptImage(props.index)

    return (
        <Layer position='top-right'
               onEsc={() => props.setShowInformation(false)}
               modal={false}
               plain={true}
               margin={{top: '60px', right: 'small', bottom: 'medium'}}
               animation="fadeIn"
        >
            <Box animation={{
                "type": "slideLeft",
                "delay": 0,
                "duration": 500,
                "size": "medium"
            }}>
                <Card elevation="large"
                      width="medium"
                >
                    <CardBody height='small'>
                        <Stack>
                            <Image
                                fill={true}
                                src={image}
                                a11yTitle="mock_nyc_image"
                            />
                            <Button icon={<Close color={"white"}/>}
                                    hoverIndicator
                                    onClick={() => {
                                        props.setShowInformation(false);
                                        props.setPois([])
                                        props.setShowAirBnBs(true)
                                    }}
                            />
                        </Stack>
                    </CardBody>
                    <Box pad={{horizontal: 'medium'}} overflow={'scroll'}>
                        <Heading level="3" margin={{top: 'medium', bottom: 'medium'}}>
                            {props.content.name}
                        </Heading>
                        <RoomInformation icon={<Money/>} roomInformation={"**Price:** " + props.content.price + "$"}/>
                        <RoomInformation icon={<Home/>} roomInformation={"**Room Type:** " + props.content.roomType}/>
                        <RoomInformation icon={<User/>} roomInformation={"**Host Name:** " + props.content.hostName}/>
                        <Paragraph margin={{top: 'none'}}>
                            Here is some basic information about the apartment by the owner.
                        </Paragraph>
                    </Box>
                    <CardFooter>
                        <Box direction="row" align="center" gap="small">
                            <Button icon={<Favorite color={favorite ? 'red' : undefined}/>}
                                    hoverIndicator
                                    onClick={() => {
                                        setFavorite(!favorite);
                                    }}
                            />
                            <Button icon={<ShareOption color="plain"/>} hoverIndicator/>
                            <Anchor
                                href="https://www.airbnb.ch/"
                                label="Book Now"
                            />
                        </Box>
                        <ExpandButton onClick={() => {
                            // loadFamousDistances()
                            setOpen(!open)
                        }
                        }/>
                    </CardFooter>
                    <Collapsible open={open}>
                        <Box pad={{horizontal: 'medium'}}
                             responsive={true}
                        >
                            <Heading level="4" margin={{vertical: 'small'}}>
                                Distances to famous attractions:
                            </Heading>
                            <Box margin={{bottom: 'medium'}}
                                 height={'small'}
                                 overflow='scroll'>
                                <List primaryKey="Name"
                                      secondaryKey="Distance"
                                      data={props.distances && props.distances.map(famous => {
                                          return ({
                                              Name: famous.name,
                                              Distance: (Math.round((famous.distance + Number.EPSILON) * 100) / 100).toString() + " km"
                                          })
                                      })}
                                />
                            </Box>
                        </Box>
                    </Collapsible>
                </Card>
            </Box>
        </Layer>
    );
}
