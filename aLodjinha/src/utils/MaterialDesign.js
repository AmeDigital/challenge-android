import { Dimensions, Platform, PixelRatio } from 'react-native';

const { width: SCREEN_WIDTH } = Dimensions.get('window');

// based on iphone 5s's scale
const scale = SCREEN_WIDTH / 320;

export function normalize(size) {
	const newSize = size * scale;
	if (Platform.OS === 'ios') {
		return Math.round(PixelRatio.roundToNearestPixel(newSize));
	} else {
		return Math.round(PixelRatio.roundToNearestPixel(newSize)) - 2;
	}
}

export default {
	colorWarmPurple: '#5e2a84',
	colorTomato: '#f15025',
	colorGreyish: '#aaaaaa',
	colorDark: '#2d3142',
	colorGreyishBrown: '#4a4a4a',
	colorWhite: '#d5d5d5',
	colorWhiteTwo: '#fcfcfc',
	fontPacificoRegular: 'Pacifico-Regular'
};
