import React from 'react';

import renderer from 'react-test-renderer';

import ProductDescription from '../../src/components/ProductDescription';

describe('Component ProductDescription', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<ProductDescription product={{ urlImagem: '', descricao: '<br />' }} />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
