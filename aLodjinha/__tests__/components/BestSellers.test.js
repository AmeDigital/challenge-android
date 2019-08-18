import React from 'react';

import renderer from 'react-test-renderer';

import BestSellers from '../../src/components/BestSellers';

describe('Component BestSellers', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<BestSellers />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
