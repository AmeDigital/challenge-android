import React from 'react';

import renderer from 'react-test-renderer';

import Product from '../../src/components/Product';

describe('Component Product', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<Product item={{ urlImagem: '' }} funcPage={function() {}} />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
