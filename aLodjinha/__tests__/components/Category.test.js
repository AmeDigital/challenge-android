import React from 'react';

import renderer from 'react-test-renderer';

import Category from '../../src/components/Category';

describe('Component Category', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<Category item={{ urlImagem: 'urlimagem' }} />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
