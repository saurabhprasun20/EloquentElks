import {Box, RangeSelector, Stack, Text} from "grommet";

const RANGE_MIN = 0;
const RANGE_MAX = 1000;

export const PriceRangeSelector = (props) => {

    return (
        <Box gap="small" pad="medium" width={'80%'}>
            {props.label ? <Text>{props.label}</Text> : null}
            <Stack>
                <Box background="light-4" height="6px" direction="row" round={'xsmall'} />
                <RangeSelector
                    direction="horizontal"
                    round={'xxsmall'}
                    min={RANGE_MIN}
                    max={RANGE_MAX}
                    step={10}
                    values={props.range}
                    onChange={nextRange => {
                        props.setRange(nextRange);}
                    }
                />
            </Stack>
            <Box align="center">
                <Text size="small">{`${props.range[0]}$ - ${props.range[1]}$`}</Text>
            </Box>
        </Box>
    );

}



