Sistema de polizas de seguros
Reglas de negocio:
Validación de Clientes:
  Todos los clientes deben tener un DNI y un nombre registrado.
  El DNI debe ser único para cada cliente.
  La dirección, teléfono y correo electrónico son campos obligatorios.
Gestión de Pólizas:
  Cada póliza debe estar vinculada a un cliente existente y un agente de ventas.
  Una póliza no puede ser creada sin especificar la prima, la fecha de inicio y la fecha de finalización.
  La fecha de finalización de la póliza debe ser posterior a la fecha de inicio.
  Cada póliza debe tener un número único.
Roles de Agente de Ventas:
  Cada agente de ventas debe estar asignado a al menos una póliza, pero puede gestionar múltiples pólizas.
Registro de Reclamaciones:
  Las reclamaciones solo pueden ser registradas por clientes que tengan una póliza activa.
  Cada reclamación debe tener un número único, fecha de presentación, estado y monto especificado.
  El monto de la reclamación no puede exceder el monto máximo especificado en la póliza correspondiente. 
  El estado de la reclamación debe ser gestionado y actualizado adecuadamente hasta su conclusión.
Gestión de Beneficiarios:
  Los beneficiarios pueden ser agregados o modificados solo por el titular de la póliza.
  Los beneficiarios deben tener un DNI y nombre registrado, y el DNI debe ser único.
  Una póliza puede tener múltiples beneficiarios, pero un beneficiario puede estar asociado a una poliza
Integridad de Datos:
  Todos los registros deben ser consistentes y completos antes de ser almacenados en la base de datos.
  Deben realizarse verificaciones regulares para asegurar que no existan duplicados o errores en los datos de las entidades principales.
Seguridad y Privacidad:
  La información personal de clientes, agentes y beneficiarios debe estar protegida según las normativas de protección de datos aplicables.
  Deben establecerse permisos adecuados para garantizar que solo el personal autorizado pueda acceder a la información sensible.
