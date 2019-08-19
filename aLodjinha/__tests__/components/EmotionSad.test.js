import React from 'react';

import renderer from 'react-test-renderer';

import EmotionSad from '../../src/components/EmotionSad';

describe('Component EmotionSad', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<EmotionSad />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
