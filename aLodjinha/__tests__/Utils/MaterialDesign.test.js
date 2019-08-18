import material, { normalize } from '../../src/utils/MaterialDesign';

describe('Test Material Design Patterns', () => {
	describe('Colors', () => {
		test('should return color Dark', () => {
			expect(material.colorDark).toEqual('#2d3142');
		});

		test('should return color Warm purple', () => {
			expect(material.colorWarmPurple).toEqual('#5e2a84');
		});

		test('should return color Tomato', () => {
			expect(material.colorTomato).toEqual('#f15025');
		});

		test('should return color Greyish', () => {
			expect(material.colorGreyish).toEqual('#aaaaaa');
		});

		test('should return color Greyish brown', () => {
			expect(material.colorGreyishBrown).toEqual('#4a4a4a');
		});

		test('should return color White', () => {
			expect(material.colorWhite).toEqual('#d5d5d5');
		});

		test('should return color White two', () => {
			expect(material.colorWhiteTwo).toEqual('#fcfcfc');
		});
	});
});
