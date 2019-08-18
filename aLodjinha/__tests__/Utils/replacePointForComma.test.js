import { replacePonitForComma } from '../../src/utils/Utils';

describe('Test ReplacePointForComma', () => {
	test('should replace between "." for "," ', () => {
		const text = 'Ame. muito. ti';

		expect(replacePonitForComma(text)).toEqual('Ame, muito, ti');
	});
});
