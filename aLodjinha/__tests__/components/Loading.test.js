import React from 'react';

import renderer from 'react-test-renderer';

import Loading from '../../src/components/Loading';

describe('Component Loading', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<Loading />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
