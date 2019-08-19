import React from 'react';

import renderer from 'react-test-renderer';

import Categories from '../../src/components/Categories';

describe('Component Categories', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<Categories />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
