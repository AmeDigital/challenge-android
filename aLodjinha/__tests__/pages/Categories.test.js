import React from 'react';

import renderer from 'react-test-renderer';

import Categories from '../../src/pages/Categories';

it('renders correctly', () => {
	const tree = renderer
		.create(
			<Categories
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
