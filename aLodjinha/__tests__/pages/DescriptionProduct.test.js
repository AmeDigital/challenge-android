import React from 'react';

import renderer from 'react-test-renderer';

import DescriptionProduct from '../../src/pages/DescriptionProduct';

it('renders correctly', () => {
	const tree = renderer
		.create(
			<DescriptionProduct
				navigation={{
					getParam: function(param) {
						return 0;
					}
				}}
			/>
		)
		.toJSON();
	expect(tree).toMatchSnapshot();
});
