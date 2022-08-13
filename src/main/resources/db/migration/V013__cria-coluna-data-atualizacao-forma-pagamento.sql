ALTER table algafood.forma_pagamento add data_atualizacao datetime null;
UPDATE algafood.forma_pagamento set data_atualizacao = UTC_TIMESTAMP ;
ALTER table algafood.forma_pagamento modify data_atualizacao datetime not null;
