import React from 'react';

import renderer from 'react-test-renderer';

import Banners from '../../src/components/Banners';

describe('Component Banners', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<Banners />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
